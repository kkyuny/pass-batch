package com.fastcampus.pass.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.batch.job.enabled=false")
class PackageRepositoryTest {
    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void test_save(){
        //given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 챌린지 PT12주");
        packageEntity.setPeriod(84);

        //when
        packageRepository.save(packageEntity);

        // then
        assertNotNull(packageEntity.getPackageSeq());
    }

    @Test
    public void test_findByCreatedAtAfter(){
        // given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1L);

        PackageEntity packageEntity0 = new PackageEntity();
        packageEntity0.setPackageName("학생 전용 3개월");
        packageEntity0.setPeriod(90);
        packageRepository.save(packageEntity0);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("학생 전용 6개월");
        packageEntity1.setPeriod(180);
        packageRepository.save(packageEntity1);

        // when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime,
                PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        // then
        assertEquals(1, packageEntities.size());
        assertEquals(packageEntity1.getPackageSeq(), packageEntities.getFirst().getPackageSeq());

    }
}