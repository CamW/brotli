package org.brotli.wrapper.dec;

import org.brotli.integration.BrotliJniTestBase;
import org.brotli.wrapper.enc.Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class KnownLengthTest extends BrotliJniTestBase {

  private static final byte[] COMPRESSED_DATA = {-117, 2, -128, 66, 82, 79, 84, 76, 73, 3};
  private static final int ORIGINAL_DATA_LENGTH = 6; // "BROTLI" length

  @Test
  public void decompressKnownLength() throws IOException {
    byte[] decompressedData = Decoder.decompress(COMPRESSED_DATA, ORIGINAL_DATA_LENGTH);
    assertEquals("BROTLI", new String(decompressedData, StandardCharsets.UTF_8));
  }

  @Test(expected = IllegalArgumentException.class)
  public void decompressKnownLengthDataTooBig() throws IOException {
    Decoder.decompress(COMPRESSED_DATA, ORIGINAL_DATA_LENGTH - 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void decompressKnownLengthDataTooSmall() throws IOException {
    Decoder.decompress(COMPRESSED_DATA, ORIGINAL_DATA_LENGTH + 1);
  }
}
