package com.mediaportal.mediaxpusers.service;

import com.mediaportal.mediaxpusers.dtos.UserDataDTO;


public interface UserService {

    public void createAssetUser(UserDataDTO userDTO);
    public Long getAssetCreated();
    public void insertAssetInTvci(Long assetId, UserDataDTO userDTO);
}
