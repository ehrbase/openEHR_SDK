/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.webtemplate.path.flat;


import org.apache.commons.lang3.StringUtils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public class FlatPathDto {

  private String name;
  private FlatPathDto child;
  private String attributeName;
  private Integer count;

  public FlatPathDto() {}

  public FlatPathDto(String path) {

    this(FlatPathParser.parse(path));
  }


  public FlatPathDto(FlatPathDto flatPathDto) {

    this.name = flatPathDto.getName();
    this.child = flatPathDto.getChild();
    this.attributeName = flatPathDto.getAttributeName();
    this.count = flatPathDto.getCount();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FlatPathDto getChild() {
    return child;
  }

  public void setChild(FlatPathDto child) {
    this.child = child;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String format() {
    StringBuilder sb = new StringBuilder();

    sb.append(name);

    if(count != null){
      sb.append(':').append(count);
    }

    if (attributeName != null) {
      sb.append('|').append(attributeName);
    }
    if (child != null) {
      sb.append('/').append(child.format());
    }
    return sb.toString();
  }

  public FlatPathDto getLast() {
    FlatPathDto path = this;
    while (path.getChild() != null) {
      path = path.getChild();
    }
    return path;
  }

  public static FlatPathDto removeEnd(FlatPathDto path, FlatPathDto remove) {

    FlatPathDto me = new FlatPathDto(path);
    FlatPathDto other = new FlatPathDto(remove);
    FlatPathDto newMe = null;
    do {

      if (isNodeEqual(me, other)) break;

        FlatPathDto newChild = new FlatPathDto(me);
        newChild.setChild(null);
        if (newMe == null){
          newMe = newChild;
        }else{newMe.getLast().setChild(newChild);
        }

      me = me.child;

    } while (me != null);

    if (me != null && me.isEqualTo(other.format())) {
      return newMe;
    } else {
      return me;
    }
  }

  public static boolean isNodeEqual(FlatPathDto me, FlatPathDto other) {

    if (!Objects.equals(me.getName(), other.getName())) {
      return false;
    }


    if (!Objects.equals(me.getCount(), other.getCount())
            && !(me.getCount() == null && Objects.equals(other.getCount(), 0)) && !(Objects.equals(me.getCount(), 0) && other.getCount() == null)
    ) {
      return false;
    }

    if (!Objects.equals(me.getAttributeName(), other.getAttributeName())
    ) {
      return false;
    }
    return true;
  }

  public static FlatPathDto removeStart(FlatPathDto path, FlatPathDto remove) {
    FlatPathDto other = new FlatPathDto(remove);
    FlatPathDto me = new FlatPathDto(path);
    do{

      if (!isNodeEqual(me, other)) break;
      other = other.getChild();
      me = me.child;
    }while (other != null && me != null);

    if (other == null) {
      return me;
    } else {
      return new FlatPathDto(path);
    }
  }

  public static FlatPathDto addEnd(FlatPathDto path, FlatPathDto add) {
    return new FlatPathDto(path.format() + "/" + StringUtils.removeStart(add.format(), "/"));
  }

  @Override
  public String toString() {
    return format();
  }

  public boolean startsWith(String otherPath) {
    FlatPathDto other = new FlatPathDto(otherPath);
    FlatPathDto me = new FlatPathDto(this);
    do{

      String tempAttributeName = me.getAttributeName();
      if (other.getAttributeName() == null    ){
        me.setAttributeName(null);
      }

      Integer tempCount = me.getCount();;
if(other.getChild() == null && other.count == null){
  me.setCount(null);
}
      boolean nodeEqual = isNodeEqual(me, other);
      me.setAttributeName(tempAttributeName);
      me.setCount(tempCount);
      if (!nodeEqual) break;


      other = other.getChild();
      me = me.child;

    }while (other != null && me != null);

    return other == null;
  }

  public boolean isEqualTo(String otherPath){

    FlatPathDto other = new FlatPathDto(otherPath);
    FlatPathDto me = new FlatPathDto(this);
    do{

      if (!Objects.equals(me.getName(),other.getName())){
        break;
      }
      if (!Objects.equals(me.getAttributeName(), other.getAttributeName())
      ){
        break;
      }

      if (!Objects.equals(me.getCount(),other.getCount())
              && !(me.getCount() == null && Objects.equals(other.getCount(),0)) && !(Objects.equals(me.getCount(), 0) && other.getCount() == null)
      ){
        break;
      }


      other = other.getChild();
      me = me.child;
    }while (other != null && me != null);

    return other == null && me == null;
  }

  public static  <T> Map.Entry<FlatPathDto,T> get(Map<FlatPathDto,T> map, String otherPath){

    return map.entrySet().stream().filter(d -> d.getKey().isEqualTo(otherPath)).findAny().orElse(new AbstractMap.SimpleEntry<>(null,null));
  }
}
