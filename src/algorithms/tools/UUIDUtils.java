package algorithms.tools;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


public final class UUIDUtils {

    private static final int[] decAsHex = new int[] {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15,
        0x16, 0x17, 0x18, 0x19, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x30, 0x31,
        0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47,
        0x48, 0x49, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x60, 0x61, 0x62, 0x63,
        0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
        0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x90, 0x91, 0x92, 0x93, 0x94, 0x95,
        0x96, 0x97, 0x98, 0x99
    };
    private static final SecureRandom DEFAULT_GENERATOR = new SecureRandom();


    private UUIDUtils() {
    }

    /**
     * Returns a modified <a href="http://en.wikipedia.org/wiki/Universally_unique_identifier">version 4
     * UUID</a> where the first 6 bytes hold the current UTC time and the remaining 10 bytes are random,
     * in the following hexadecimal form:
     *
     * <pre>    yyMMddhh-mmss-4xxx-yxxx-xxxxxxxxxxxx</pre>
     * where x is any hexadecimal digit and y is one of 8, 9, a or b. Example:
     * <pre>    10070605-2232-42fe-9a13-c4bf80c6b99c</pre>
     *
     * The resulting UUIDs contain 74 bits of entropy, after subtracting 6 bits for versioning
     * metadata, and so can be modeled as the current time (to second accuracy) concatenated with a
     * random number between 0 and 18,889,465,931,478,580,854,784 (~19e21).
     *
     * @see java.util.UUID
     *
     * @param generator the PRNG to use to generate the random bytes of the UUID.
     */
    public static java.util.UUID randomTimestampedUUID(final Random generator) {
        byte[] randomBytes = new byte[10];
        generator.nextBytes(randomBytes);
        DateTime dt = new DateTime(DateTimeZone.UTC);

        long msb = decAsHex[dt.getYear() - 2000];
        msb = (msb << 8) | decAsHex[dt.getMonthOfYear()];
        msb = (msb << 8) | decAsHex[dt.getDayOfMonth()];
        msb = (msb << 8) | decAsHex[dt.getHourOfDay()];
        msb = (msb << 8) | decAsHex[dt.getMinuteOfHour()];
        msb = (msb << 8) | decAsHex[dt.getSecondOfMinute()];
        msb = (msb << 8) | ((randomBytes[0] & 0x0f) | 0x40);  // set to version 4
        msb = (msb << 8) | (randomBytes[1] & 0xff);

        long lsb = (randomBytes[2] & 0x3f) | 0x80;  // set to IETF variant
        lsb = (lsb << 8) | (randomBytes[3] & 0xff);
        lsb = (lsb << 8) | (randomBytes[4] & 0xff);
        lsb = (lsb << 8) | (randomBytes[5] & 0xff);
        lsb = (lsb << 8) | (randomBytes[6] & 0xff);
        lsb = (lsb << 8) | (randomBytes[7] & 0xff);
        lsb = (lsb << 8) | (randomBytes[8] & 0xff);
        lsb = (lsb << 8) | (randomBytes[9] & 0xff);

        return new java.util.UUID(msb, lsb);
    }

    /**
     * Returns a modified <a href="http://en.wikipedia.org/wiki/Universally_unique_identifier">version 4
     * UUID</a> where the first 6 bytes hold the current UTC time and the remaining 10 bytes are random,
     * in the following hexadecimal form:
     *
     * <pre>    yyMMddhh-mmss-4xxx-yxxx-xxxxxxxxxxxx</pre>
     * where x is any hexadecimal digit and y is one of 8, 9, a or b. Example:
     * <pre>    10070605-2232-42fe-9a13-c4bf80c6b99c</pre>
     *
     * The resulting UUIDs contain 74 bits of entropy, after subtracting 6 bits for versioning
     * metadata, and so can be modeled as the current time (to second accuracy) concatenated with a
     * random number between 0 and 18,889,465,931,478,580,854,784 (~19e21).
     *
     * @see java.util.UUID
     */
    public static java.util.UUID randomTimestampedUUID() {
        return randomTimestampedUUID(DEFAULT_GENERATOR);
    }

    /**
     * Returns an iterator that yields a random timestamped UUID each time {@code next()} is called.
     * The {@code hasNext()} method always returns true, and the {@code remove()} operation is not
     * supported.
     *
     * The default pseudo random number generator used by this method is SHA-1. Please use {@link #randomTimestampedUUIDGenerator(Random)} if you
     * need a different PRNG.
     *
     * @see #randomTimestampedUUID
     */
    public static Iterator<java.util.UUID> randomTimestampedUUIDGenerator() {
        return randomTimestampedUUIDGenerator(DEFAULT_GENERATOR);
    }

    /**
     * Returns an iterator that yields a random timestamped UUID each time {@code next()} is called.
     * The {@code hasNext()} method always returns true, and the {@code remove()} operation is not
     * supported.
     *
     *
     * @see #randomTimestampedUUID
     *
     * @param generator the PRNG to use to generate the random bytes of the UUID.
     */
    public static Iterator<java.util.UUID> randomTimestampedUUIDGenerator(final Random generator) {
        return new Iterator<java.util.UUID>() {
            @Override public boolean hasNext() {
                return true;
            }

            @Override public java.util.UUID next() {
                return randomTimestampedUUID(generator);
            }

            @Override public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Static factory to retrieve a type 5 (name based) <tt>UUID</tt> based on
     * the specified byte array.
     *
     * @param  name a byte array to be used to construct a <tt>UUID</tt>.
     * @return  a <tt>UUID</tt> generated from the specified array.
     */
    public static java.util.UUID nameUUIDFromBytes(byte[] name) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (final NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA1 not supported");
        }
        byte[] mdSha1Bytes = md.digest(name);
        mdSha1Bytes[6]  &= 0x0f;  /* clear version        */
        mdSha1Bytes[6]  |= 0x50;  /* set to version 5     */
        mdSha1Bytes[8]  &= 0x3f;  /* clear variant        */
        mdSha1Bytes[8]  |= 0x80;  /* set to IETF variant  */
        final ByteBuffer buffer = ByteBuffer.wrap(mdSha1Bytes);
        return new java.util.UUID(buffer.getLong(), buffer.getLong());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1100; i++) {
            System.out.println(randomTimestampedUUID());
        }
    }
}

