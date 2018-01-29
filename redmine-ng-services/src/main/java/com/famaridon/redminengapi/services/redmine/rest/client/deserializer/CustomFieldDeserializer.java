package com.famaridon.redminengapi.services.redmine.rest.client.deserializer;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.ArrayCustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.CustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.StringCustomField;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomFieldDeserializer extends JsonDeserializer<CustomField> {
	
	@Override
	public CustomField deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		JsonNode isMultiple = node.get("multiple");
		CustomField customField;
		if(isMultiple != null && isMultiple.asBoolean()) {
			customField = new ArrayCustomField();
		} else {
			customField= new StringCustomField();
		}
		customField.setId(node.get("id").longValue());
		customField.setName(node.get("name").asText());
		return customField;
	}
}
