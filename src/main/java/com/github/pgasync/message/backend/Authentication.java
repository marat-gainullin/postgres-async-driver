/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.pgasync.message.backend;

import com.github.pgasync.message.Message;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author Antti Laisi
 */
public class Authentication implements Message {

    public static final Authentication OK = new Authentication(true, false, null);
    public static final Authentication CLEAR_TEXT = new Authentication(false, false, null);
    public static final Authentication SCRAM_SHA_256 = new Authentication(false, true, null);
    public static final String SUPPORTED_SASL = "SCRAM-SHA-256";

    private final boolean success;
    private final boolean scramSha256;
    private final byte[] md5salt;

    public Authentication(boolean success, boolean scramSha256, byte[] md5salt) {
        this.success = success;
        this.scramSha256 = scramSha256;
        this.md5salt = md5salt;
    }

    public byte[] getMd5Salt() {
        return md5salt;
    }

    public boolean isAuthenticationOk() {
        return success;
    }

    public boolean isScramSha256() {
        return scramSha256;
    }

    @Override
    public String toString() {
        return String.format("Authentication(success=%s, md5salt=%s, scramSha256=%s)", success, md5salt != null ? printHexBinary(md5salt) : null, scramSha256);
    }
}
