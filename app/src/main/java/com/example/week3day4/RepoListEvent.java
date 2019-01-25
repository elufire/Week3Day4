package com.example.week3day4;

import com.example.week3day4.GithubRepository.Repository;

public class RepoListEvent {
    Repository repository;

    public RepoListEvent(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
