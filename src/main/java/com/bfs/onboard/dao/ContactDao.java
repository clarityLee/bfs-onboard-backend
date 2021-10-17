package com.bfs.onboard.dao;

import com.bfs.onboard.domain.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> fetchListByOwner(Integer ownerId);
}
