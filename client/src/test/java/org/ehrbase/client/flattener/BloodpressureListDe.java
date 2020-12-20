/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.flattener;

import java.time.OffsetDateTime;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;

@Archetype(value = "openEHR-EHR-COMPOSITION.sample_encounter.v1")
@Template(value = "ehrbase_blood_pressure_simple.de.v0")
@Entity
public class BloodpressureListDe {

  @Path(value = "/context/start_time|value")
  private OffsetDateTime startTime;

  @Path(value = "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]")
  private List<Bloodpressure> bloodpressures;

  public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public List<Bloodpressure> getBloodpressures() {
    return bloodpressures;
  }

  public void setBloodpressures(List<Bloodpressure> bloodpressures) {
    this.bloodpressures = bloodpressures;
  }

  @Archetype(value = "openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
  @Entity
  public static class Bloodpressure {

    @Path(value = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double systolischValue;

    public Double getSystolischValue() {
      return systolischValue;
    }

    public void setSystolischValue(Double systolischValue) {
      this.systolischValue = systolischValue;
    }
  }
}
