/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.kerby.cms.type;

import org.apache.kerby.asn1.Asn1FieldInfo;
import org.apache.kerby.asn1.EnumType;
import org.apache.kerby.asn1.type.Asn1SequenceType;

/**
 * ExtendedCertificateInfo ::= SEQUENCE {
 *   version CMSVersion,
 *   certificate Certificate,
 *   attributes UnauthAttributes
 * }
 */
public class ExtendedCertificateInfo extends Asn1SequenceType {
    protected enum ExtendedCertificateInfoField implements EnumType {
        CMS_VERSION,
        CERTIFICATE,
        ATTRIBUTES;

        @Override
        public int getValue() {
            return ordinal();
        }

        @Override
        public String getName() {
            return name();
        }
    }

    static Asn1FieldInfo[] fieldInfos = new Asn1FieldInfo[] {
            new Asn1FieldInfo(ExtendedCertificateInfoField.CMS_VERSION, CmsVersion.class),
            new Asn1FieldInfo(ExtendedCertificateInfoField.CERTIFICATE, SignatureAlgorithmIdentifier.class),
            new Asn1FieldInfo(ExtendedCertificateInfoField.ATTRIBUTES, Signature.class)
    };

    public ExtendedCertificateInfo() {
        super(fieldInfos);
    }

    public CmsVersion getCmsVersion() {
        return getFieldAs(ExtendedCertificateInfoField.CMS_VERSION, CmsVersion.class);
    }

    public void setCmsVersion(CmsVersion version) {
        setFieldAs(ExtendedCertificateInfoField.CMS_VERSION, version);
    }

    public SignatureAlgorithmIdentifier getCertificate() {
        return getFieldAs(ExtendedCertificateInfoField.CERTIFICATE, SignatureAlgorithmIdentifier.class);
    }

    public void setCertificate(SignatureAlgorithmIdentifier signatureAlgorithmIdentifier) {
        setFieldAs(ExtendedCertificateInfoField.CERTIFICATE, signatureAlgorithmIdentifier);
    }

    public Signature getAttributes() {
        return getFieldAs(ExtendedCertificateInfoField.ATTRIBUTES, Signature.class);
    }

    public void setAttributes(Signature signature) {
        setFieldAs(ExtendedCertificateInfoField.ATTRIBUTES, signature);
    }

}
