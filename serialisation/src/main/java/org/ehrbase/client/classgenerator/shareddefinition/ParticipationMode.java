/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.classgenerator.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ParticipationMode implements EnumValueSet {
    NOT_SPECIFIED("not specified", "not specified", "openehr", "193"),

    FACE_TO_FACE_COMMUNICATION("face-to-face communication", "face-to-face communication", "openehr", "216"),
    INTERPRETED_FACE_TO_FACE_COMMUNICATION(
            "interpreted face-to-face communication", "interpreted face-to-face communication", "openehr", "223"),
    SIGNING_FACE_TO_FACE("signing (face-to-face)", "signing (face-to-face)", "openehr", "217"),
    LIVE_AUDIOVISUAL_VIDEOCONFERENCE_VIDEOPHONE(
            "live audiovisual; videoconference; videophone",
            "live audiovisual; videoconference; videophone",
            "openehr",
            "195"),
    VIDEOCONFERENCING("videoconferencing", "videoconferencing", "openehr", "198"),
    VIDEOPHONE("videophone", "videophone", "openehr", "197"),
    SIGNING_OVER_VIDEO("signing over video", "signing over video", "openehr", "218"),
    INTERPRETED_VIDEO_COMMUNICATION(
            "interpreted video communication", "interpreted video communication", "openehr", "224"),
    ASYNCHRONOUS_AUDIOVISUAL_RECORDED_VIDEO(
            "asynchronous audiovisual; recorded video", "asynchronous audiovisual; recorded video", "openehr", "194"),
    RECORDED_VIDEO("recorded video", "recorded video", "openehr", "196"),
    LIVE_AUDIO_ONLY_TELEPHONE_INTERNET_PHONE_TELECONFERENCE(
            "live audio-only; telephone; internet phone; teleconference",
            "live audio-only; telephone; internet phone; teleconference",
            "openehr",
            "202"),
    TELEPHONE("telephone", "telephone", "openehr", "204"),
    TELECONFERENCE("teleconference", "teleconference", "openehr", "203"),
    INTERNET_TELEPHONE("internet telephone", "internet telephone", "openehr", "205"),
    INTERPRETED_AUDIO_ONLY("interpreted audio-only", "interpreted audio-only", "openehr", "222"),
    ASYNCHRONOUS_AUDIO_ONLY_DICTATED_VOICE_MAIL(
            "asynchronous audio-only; dictated; voice mail",
            "asynchronous audio-only; dictated; voice mail",
            "openehr",
            "216"),
    DICTATED("dictated", "dictated", "openehr", "200"),
    VOICE_MAIL("voice-mail", "voice-mail", "openehr", "201"),
    LIVE_TEXT_ONLY_INTERNET_CHAT_SMS_CHAT_INTERACTIVE_WRITTEN_NOTE(
            "live text-only; internet chat; SMS chat; interactive written note",
            "live text-only; internet chat; SMS chat; interactive written note",
            "openehr",
            "212"),
    INTERNET_CHAT("internet chat", "internet chat", "openehr", "213"),
    SMS_CHAT("SMS chat", "SMS chat", "openehr", "215"),
    INTERACTIVE_WRITTEN_NOTE("interactive written note", "interactive written note", "openehr", "215"),
    ASYNCHRONOUS_TEXT_EMAIL_FAX_LETTER_HANDWRITTEN_NOTE_SMS_MESSAGE(
            "asynchronous text; email; fax; letter; handwritten note; SMS message",
            "asynchronous text; email; fax; letter; handwritten note; SMS message",
            "openehr",
            "206"),
    HANDWRITTEN_NOTE("handwritten note", "handwritten note", "openehr", "211"),
    PRINTED_TYPED_LETTER("printed/typed letter", "printed/typed letter", "openehr", "210"),
    EMAIL("email", "email", "openehr", "207"),
    FACSIMILE_TELEFAX("facsimile/telefax", "facsimile/telefax", "openehr", "208"),
    TRANSLATED_TEXT("translated text", "translated text", "openehr", "221"),
    SMS_MESSAGE("SMS message", "SMS message", "openehr", "209"),
    PHYSICALLY_PRESENT("physically present", "physically present", "openehr", "219"),
    PHYSICALLY_REMOTE("physically remote", "physically remote", "openehr", "216");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    ParticipationMode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
