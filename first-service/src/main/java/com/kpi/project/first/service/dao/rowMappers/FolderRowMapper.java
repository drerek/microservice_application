package com.kpi.project.first.service.dao.rowMappers;

import com.kpi.project.first.service.entity.Folder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.kpi.project.first.service.keys.Key.*;

public class FolderRowMapper implements RowMapper<Folder> {
    @Override
    public Folder mapRow(ResultSet resultSet, int i) throws SQLException {
        Folder folder = new Folder();

        folder.setFolderId(resultSet.getInt(FOLDER_FOLDER_ID));
        folder.setUserId(resultSet.getInt(FOLDER_USER_ID));
        folder.setName(resultSet.getString(FOLDER_NAME));

        return folder;
    }
}
