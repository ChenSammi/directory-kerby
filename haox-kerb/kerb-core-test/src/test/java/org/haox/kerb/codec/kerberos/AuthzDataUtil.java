package org.haox.kerb.codec.kerberos;

import org.haox.kerb.codec.pac.Pac;
import org.haox.kerb.spec.KrbException;
import org.haox.kerb.spec.type.common.AuthorizationData;
import org.haox.kerb.spec.type.common.AuthorizationDataEntry;
import org.haox.kerb.spec.type.common.AuthorizationType;
import org.haox.kerb.spec.type.common.EncryptionKey;

import java.io.IOException;
import java.security.Key;
import java.util.List;

public class AuthzDataUtil {

    public static Pac getPac(AuthorizationData authzData, byte[] serverKey) throws IOException, KrbException {
        AuthorizationDataEntry ifRelevantAd = null;
        for (AuthorizationDataEntry entry : authzData.getElements()) {
            if (entry.getAuthzType() == AuthorizationType.AD_IF_RELEVANT) {
                ifRelevantAd = entry;
                break;
            }
        }

        if (ifRelevantAd != null) {
            List<AuthorizationDataEntry> entries = decode(ifRelevantAd);
            for (AuthorizationDataEntry entry : entries) {
                if (entry.getAuthzType() == AuthorizationType.AD_WIN2K_PAC) {
                    return decodeAsPac(entry, serverKey);
                }
            }
        }

        return null;
    }

    public static List<AuthorizationDataEntry> decode(AuthorizationDataEntry entry) throws IOException {
        AuthorizationData authzData = new AuthorizationData();
        authzData.decode(entry.getAuthzData());
        return authzData.getElements();
    }

    public static Pac decodeAsPac(AuthorizationDataEntry entry, byte[] key) throws IOException, KrbException {
        if (entry.getAuthzType() != AuthorizationType.AD_WIN2K_PAC) {
            throw new IllegalArgumentException("Not AD_WIN2K_PAC type: " + entry.getAuthzType().name());
        }

        return new Pac(entry.getAuthzData(), key);
    }
}
