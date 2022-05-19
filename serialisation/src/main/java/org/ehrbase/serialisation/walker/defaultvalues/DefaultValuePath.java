/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.walker.defaultvalues;

import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.support.identification.ObjectRef;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.State;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;
import java.util.List;

public class DefaultValuePath<T> {

  public static final DefaultValuePath<Language> LANGUAGE =
      new DefaultValuePath<>("language", Language.class);
  public static final DefaultValuePath<Territory> TERRITORY =
      new DefaultValuePath<>("territory", Territory.class);
  public static final DefaultValuePath<String> COMPOSER_NAME =
      new DefaultValuePath<>("composer_name", String.class);
  public static final DefaultValuePath<String> COMPOSER_ID =
      new DefaultValuePath<>("composer_id", String.class);
  public static final DefaultValuePath<String> ID_NAMESPACE =
      new DefaultValuePath<>("id_namespace", String.class);
  public static final DefaultValuePath<String> ID_SCHEME =
      new DefaultValuePath<>("id_scheme", String.class);
  public static final DefaultValuePath<Boolean> COMPOSER_SELF =
      new DefaultValuePath<>("composer_self", Boolean.class);
  public static final DefaultValuePath<TemporalAccessor> TIME =
      new DefaultValuePath<>("time", TemporalAccessor.class);
  public static final DefaultValuePath<TemporalAccessor> END_TIME =
      new DefaultValuePath<>("end_time", TemporalAccessor.class);
  public static final DefaultValuePath<TemporalAccessor> HISTORY_ORIGIN =
      new DefaultValuePath<>("history_origin", TemporalAccessor.class);
  public static final DefaultValuePath<TemporalAccessor> ACTION_TIME =
      new DefaultValuePath<>("action_time", TemporalAccessor.class);
  public static final DefaultValuePath<String> ACTIVITY_TIMING =
      new DefaultValuePath<>("activity_timing", String.class);
  public static final DefaultValuePath<String> PROVIDER_NAME =
      new DefaultValuePath<>("provider_name", String.class);
  public static final DefaultValuePath<String> PROVIDER_ID =
      new DefaultValuePath<>("provider_id", String.class);
  public static final DefaultValuePath<String> HEALTHCARE_FACILITY_NAME =
      new DefaultValuePath<>("health_care_facility|name", String.class);
  public static final DefaultValuePath<String> HEALTHCARE_FACILITY_ID =
      new DefaultValuePath<>("health_care_facility|id", String.class);
  public static final DefaultValuePath<State> ACTION_ISM_TRANSITION_CURRENT_STATE =
      new DefaultValuePath<>("action_ism_transition_current_state", State.class);
  public static final DefaultValuePath<String> INSTRUCTION_NARRATIVE =
      new DefaultValuePath<>("instruction_narrative", String.class);
  public static final DefaultValuePath<String> LOCATION =
      new DefaultValuePath<>("location", String.class);
  public static final DefaultValuePath<Setting> SETTING =
      new DefaultValuePath<>("setting", Setting.class);
  public static final DefaultValuePath<List<Participation>> PARTICIPATION =
      new DefaultValuePath<>("participation");
  public static final DefaultValuePath<List<Link>> LINKS = new DefaultValuePath<>("link");
  public static final DefaultValuePath<ObjectRef> WORKFLOW_ID =
      new DefaultValuePath<>("work_flow_id", ObjectRef.class);

  private final String path;
  private final Class<T> type;

  // For List valued Fields
  private DefaultValuePath(String path) {
    this.path = path;
    this.type = null;
  }

  private DefaultValuePath(String path, Class<T> type) {
    this.path = path;
    this.type = type;
  }

  public String getPath() {
    return path;
  }

  public Class<?> getType() {
    if (type == null) {
      return List.class;
    }
    return type;
  }

  @Override
  public String toString() {
    return path;
  }
}
