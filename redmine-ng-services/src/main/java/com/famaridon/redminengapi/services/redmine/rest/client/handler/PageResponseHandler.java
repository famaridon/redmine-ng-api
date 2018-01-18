package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;

import java.io.IOException;

public class PageResponseHandler<T> extends AbstractRedmineResponseHandler<Page<T>> {
	
	private final Class<T> elementType;
	
	public PageResponseHandler(ConfigurationService configurationService, Class<T> elementType) {
		super(configurationService);
		this.elementType = elementType;
	}
	
	@Override
	protected Page parse(HttpEntity entity) throws IOException {
		JsonNode pageNode = this.configurationService.getObjectMapper().readTree(entity.getContent());
		JsonNode pageNodeElements = pageNode.get("projects");
		Page<T> page = new Page<>();
		for (int i = 0; i < pageNodeElements.size(); i++) {
			page.getElements().add(this.configurationService.getObjectMapper().treeToValue(pageNodeElements.get(i), elementType));
		}
		
		page.setTotalCount(pageNode.get("total_count").asLong());
		page.setOffset(pageNode.get("offset").asLong());
		page.setLimit(pageNode.get("limit").asLong());
		
		return page;
	}
}
