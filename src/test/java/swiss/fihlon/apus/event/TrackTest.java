package swiss.fihlon.apus.event;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackTest {

    @Test
    void noneTrack() {
        final Track testee = new Track(null);
        assertEquals(Track.NONE, testee);
        assertNull(testee.svgCode());
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForDefaultTrack")
    void defaultTracks(@NotNull final Track testee) {
        final String message = String.format("Error while testing track '%s'!", testee);
        assertNotNull(testee, message);
        assertNotNull(testee.svgCode(), message);
        assertTrue(testee.svgCode().startsWith("<?xml"), message);
        assertTrue(testee.svgCode().contains("<svg"), message);
        assertTrue(testee.svgCode().endsWith("</svg>"), message);
    }

    private static Stream<Arguments> provideArgumentsForDefaultTrack() {
        return Stream.of(
                Arguments.of(Track.ARCHITECTURE),
                Arguments.of(Track.CLOUD),
                Arguments.of(Track.CORE),
                Arguments.of(Track.INFRASTRUCTURE),
                Arguments.of(Track.SECURITY),
                Arguments.of(Track.TOOLS)
        );
    }

    @Test
    void customTrack() {
        final Track testee = new Track("testSvgCode");
        assertEquals("testSvgCode", testee.svgCode());
    }

}
