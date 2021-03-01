package org.ehrbase.client.templateprovider;

import java.util.Optional;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class ClientTemplateProvider implements TemplateProvider {

  private DefaultRestClient restClient;

  public ClientTemplateProvider(DefaultRestClient restClient) {
    this.restClient = restClient;
  }

  @Override
  public Optional<OPERATIONALTEMPLATE> find(String templateId) {
    return restClient.templateEndpoint().findTemplate(templateId);
  }

}
