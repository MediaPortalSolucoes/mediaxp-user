package com.mediaportal.mediaxpusers.serviceImpl;

import com.mediaportal.mediaxpusers.client.UserClient;
import com.mediaportal.mediaxpusers.dtos.UserDataDTO;
import com.mediaportal.mediaxpusers.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserClient client;

    @Override
    public void createAssetUser(UserDataDTO userDTO) {
        try {
            String query = String.format("EXECUTE PROCEDURE mx_createasset(925, '%s', ' ', 0, 0, 0, 'informix')",
                userDTO.getName());

            jdbcTemplate.query(query, rs -> {
                insertAssetInTvci(rs.getLong(1), userDTO);

            });
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void insertAssetInTvci(Long assetId, UserDataDTO userDTO) {
        String query = "INSERT INTO tvci_identidade (assetid, nome, cpf, email) VALUES (?, ?, ?, ?)";
        log.debug("INSERT " + query);
        jdbcTemplate.update(query, assetId, userDTO.getName(), userDTO.getCpf(), userDTO.getEmail());
        log.debug("publish asset in mongo");
        publishAsset(assetId);
    }

    public void publishAsset(Long assetId) {
        client.publishAsset(assetId);
    }
}
