package com.tikalabs.core.database.jdbc;

import com.tikalabs.core.database.utils.IServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EmbeddedDBServiceLayer implements IServiceLayer<Map<String, String>> {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedDBServiceLayer.class);
    private final String tblName;
    private final EmbeddedDB embeddedDB;

    public EmbeddedDBServiceLayer(String dbName, String tblName) {
        this.tblName = tblName;
        embeddedDB = new EmbeddedDB(dbName);

    }

    @Override
    public boolean insertRow(Map<String, String> row) throws Exception {

        return embeddedDB.insertRecord(this.tblName, row);
    }

}
