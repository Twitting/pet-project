package ru.twitting.petproject.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateUserEntity;

@UnitTest
@DisplayName("UserResponseDtoBuilder Unit test")
class UserResponseDtoBuilderTest {

    //  ------------------------------------------------ POSITIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("build(): returns valid response entity on valid request, no point")
    void successfulBuild() {
        var entity = generateUserEntity();

        var actual = assertDoesNotThrow(() -> new UserResponseDtoBuilder(entity).build());

        assertNotNull(actual);
        assertEquals(entity.getShownName(), actual.getName());
        assertEquals(entity.getEmail(), actual.getEmail());
        assertEquals(entity.getPhone(), actual.getPhone());
    }
}