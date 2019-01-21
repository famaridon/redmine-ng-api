package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.CrudEndpoint;
import com.famaridon.redminengapi.rest.dto.AbstractDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.CrudService;
import com.famaridon.redminengapi.services.indicators.beans.AbstractBean;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractCrudEndpoint<DTO extends AbstractDto, S extends CrudService<B>, B extends AbstractBean> implements CrudEndpoint<DTO> {

	@Context
	protected UriInfo uriInfo;
	@Inject
	protected DtoMapper mapper;

	protected abstract S getService();

	protected abstract B dtoToBean(DTO dto);

	protected List<B> dtosToBeans(List<DTO> dtos) {
		return dtos.stream().map(this::dtoToBean).collect(Collectors.toList());
	}

	protected abstract DTO beanToDto(B bean);

	protected List<DTO> beansToDtos(List<B> beans) {
		return beans.stream().map(this::beanToDto).collect(Collectors.toList());
	}


	@Override
	public PageDto<DTO> findAll(Long offset, Long limit) throws IOException {
		Page<B> page = this.getService().findAll(new Pager(offset, limit));
		PageDto<DTO> pageDto = this.mapper.pageToPageDto(page);
		pageDto.setElements(this.beansToDtos(page.getElements()));
		return pageDto;
	}
	
	@Override
	public Response create(DTO dto) {
		B bean = this.dtoToBean(dto);
		bean = this.getService().create(bean);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(Long.toString(bean.getId()));
		return Response.created(uriBuilder.build()).entity(this.beanToDto(bean)).build();
	}
	
	@Override
	public DTO findById(Long id) {
		Optional<B> optional = this.getService().findById(id);
		if(!optional.isPresent()) {
			throw new NotFoundException();
		}
		return this.beanToDto(optional.get());
	}
	
	@Override
	public Response update( Long id, DTO iterationDto) {
		B bean = this.dtoToBean(iterationDto);
		bean.setId(id);
		try {
			this.getService().update(bean);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(e);
		}
		return Response.ok(bean).build();
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.getService().deleteById(id);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(e);
		}
	}
}
