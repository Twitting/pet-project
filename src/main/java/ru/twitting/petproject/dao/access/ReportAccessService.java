package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.dao.repository.ReportRepository;
import ru.twitting.petproject.dao.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j(topic = "PET-ACCESS-SERVICE")
@RequiredArgsConstructor
@Transactional
public class ReportAccessService {

    private final ReportRepository repository;

    public ReportEntity save(ReportEntity entity) {
        return repository.save(entity);
    }
}
