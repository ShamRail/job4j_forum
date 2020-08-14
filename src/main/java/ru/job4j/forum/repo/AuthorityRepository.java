package ru.job4j.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.forum.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
