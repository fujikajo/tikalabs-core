package com.tikalabs.core.io.fixedwidth.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FixedWidthSchema {

	private static final Logger logger = LoggerFactory.getLogger(FixedWidthSchema.class);

	public static final String FIELDS = "fields";

	public static final String FIELD_NAME = "name";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_LENGTH = "length";
	public static final String FIELD_ALIGNMENT = "alignment";

	public static final char DEFAULT_PADDING_CHARACTER = ' ';
	public static final String DEFAULT_ALIGNMENT = "left";

	private JsonNode fieldsNode;
	private final List<FixedWidthField> fixedWidthFields;

	public FixedWidthSchema(String jsonSchema) {
		this.fixedWidthFields = new ArrayList<>();
		try {
			initialize(jsonSchema);
		} catch (JSONException | IOException e) {
			logger.error("{}", e);
			throw new IllegalArgumentException(e);
		}

	}

	private void initialize(String jsonSchema) throws JSONException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(jsonSchema);
		this.fieldsNode = rootNode.path(FIELDS);
		for (JsonNode fieldNode : fieldsNode) {
			FixedWidthField field = objectMapper.readValue(fieldNode.toPrettyString(), FixedWidthField.class);
			fixedWidthFields.add(field);
		}
	}

	public JsonNode getFieldsNode() {
		return fieldsNode;
	}

	public void setFieldsNode(JsonNode fieldsNode) {
		this.fieldsNode = fieldsNode;
	}

	public List<FixedWidthField> getFixedWidthFields() {
		return fixedWidthFields;
	}

}
