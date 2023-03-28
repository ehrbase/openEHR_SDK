/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.serialisation.xmlencoding;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.xml.JAXBUtil;
import com.nedap.archie.xml.adapters.DateTimeXmlAdapter;
import com.nedap.archie.xml.adapters.DateXmlAdapter;
import com.nedap.archie.xml.adapters.TimeXmlAdapter;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.util.SnakeCase;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

public class CanonicalXML implements RMDataFormat {

    // should be http://schemas.openehr.org/v1 but this does not work with archie.
    private static final String NAMESPACE = "";

    @Override
    public String marshal(RMObject rmObject) {

        return marshal(rmObject, true);
    }

    public String marshal(RMObject rmObject, Boolean withHeader) {

        StringWriter stringWriter = new StringWriter();
        try {
            Marshaller marshaller = JAXBUtil.getArchieJAXBContext().createMarshaller();
            marshaller.setAdapter(DateTimeXmlAdapter.class, new SdkDateTimeXmlAdapter());
            marshaller.setAdapter(DateXmlAdapter.class, new SdkDateXmlAdapter());
            marshaller.setAdapter(TimeXmlAdapter.class, new SdkTimeXmlAdapter());
            marshaller.setProperty("jaxb.fragment", !withHeader);
            if (rmObject.getClass().getAnnotation(XmlRootElement.class) == null) {
                QName qName = new QName(null, new SnakeCase(rmObject.getClass().getSimpleName()).camelToSnake());
                JAXBElement<RMObject> root = new JAXBElement<>(qName, RMObject.class, rmObject);
                marshaller.marshal(root, stringWriter);
            } else {

                marshaller.marshal(rmObject, stringWriter);
            }
        } catch (JAXBException e) {
            throw new org.ehrbase.serialisation.exception.MarshalException(e.getMessage(), e);
        }

        return stringWriter.toString();
    }

    public String marshalInline(RMObject rmObject, QName qName) {

        try {
            JAXBElement<RMObject> root = new JAXBElement<>(qName, RMObject.class, rmObject);

            Marshaller marshaller = JAXBUtil.getArchieJAXBContext().createMarshaller();
            marshaller.setAdapter(DateTimeXmlAdapter.class, new SdkDateTimeXmlAdapter());
            marshaller.setAdapter(DateXmlAdapter.class, new SdkDateXmlAdapter());
            marshaller.setAdapter(TimeXmlAdapter.class, new SdkTimeXmlAdapter());

            DOMResult res = new DOMResult();
            marshaller.marshal(root, res);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            Node rootNode = res.getNode().getFirstChild();
            NodeList childNodes = rootNode.getChildNodes();

            StringWriter stringWriter = new StringWriter();
            for (int i = 0; i < childNodes.getLength(); i++) {
                transformer.transform(new DOMSource(childNodes.item(i)), new StreamResult(stringWriter));
            }
            return stringWriter.toString();

        } catch (JAXBException | TransformerException e) {
            throw new org.ehrbase.serialisation.exception.MarshalException(e.getMessage(), e);
        }
    }

    @Override
    public <T extends RMObject> T unmarshal(String value, Class<T> clazz) {
        T composition;
        try {
            Unmarshaller unmarshaller = JAXBUtil.getArchieJAXBContext().createUnmarshaller();
            unmarshaller.setAdapter(DateTimeXmlAdapter.class, new SdkDateTimeXmlAdapter());
            unmarshaller.setAdapter(DateXmlAdapter.class, new SdkDateXmlAdapter());
            unmarshaller.setAdapter(TimeXmlAdapter.class, new SdkTimeXmlAdapter());
            // Set the parentss XMLReader on the XMLFilter
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // disable external entities
            spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
            spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            spf.setNamespaceAware(true);
            spf.setValidating(false);
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLFilter filter = new NamespaceFilter();
            filter.setParent(xr);

            UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
            filter.setContentHandler(unmarshallerHandler);
            filter.parse(new InputSource(IOUtils.toInputStream(value, UTF_8)));
            composition = (T) unmarshallerHandler.getResult();
        } catch (JAXBException | ParserConfigurationException | SAXException | IOException e) {
            throw new UnmarshalException(e.getMessage(), e);
        }
        return composition;
    }

    private class NamespaceFilter extends XMLFilterImpl {

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(NAMESPACE, localName, qName);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

            AttributesImpl attributesImpl = new AttributesImpl(atts);

            super.startElement(NAMESPACE, localName, qName, attributesImpl);
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            // remove default namespace http://schemas.openehr.org/v1
            if (!prefix.equals("")) {
                super.startPrefixMapping(prefix, uri);
            }
        }
    }
}
