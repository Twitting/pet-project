package ru.twitting.petproject.dao.access;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.twitting.petproject.test.tags.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.*;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateUserEntity;

@SpringIntegrationTest
@Testcontainers
@DisplayName("UserAccessService Integration test")
class UserAccessServiceTest {


    @Autowired
    private UserAccessService userAccessService;


    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("save(): returns valid entity")
    void successfulSave() {
        var entity = generateUserEntity();

        var actual = assertDoesNotThrow(() -> userAccessService.save(entity));

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(entity.getUsername(), actual.getUsername());
    }

    @Test
    @DisplayName("findByUsername(): returns valid entity")
    void successfulFindByUsername() {
        var entity = generateUserEntity();
        userAccessService.save(entity);

        var actual = userAccessService.findByUsername(entity.getUsername());

        assertNotNull(actual);
        assertEquals(entity.getUsername(), actual.getUsername());
    }

}