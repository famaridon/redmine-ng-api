package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.ReleaseNoteEndpoint;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.redmine.VersionsService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;


/**
 * Release note Endpoint implementation
 * Permet de retourner la réponse contenant le fichier produit de la release note
 */
@RequestScoped
public class ReleaseNoteEndpointImpl implements ReleaseNoteEndpoint {

  @Inject
  private ReleaseNoteService releaseNoteService;
  @Inject
  private VersionsService versionsService;
  @Inject
  private DtoMapper mapper;

  @Override
  public Response releaseNoteZip(String version, String product, Long idV) {
   return releaseNoteService.generateReleaseNote(FileType.ZIP, version, product, idV);
  }

  @Override
  public Response releaseNotePdf(String version, String product, Long idV) {
    return releaseNoteService.generateReleaseNote(FileType.PDF, version, product, idV);
  }

  @Override
  public Response releaseNoteDoc(String version, String product, Long idV) {
    return releaseNoteService.generateReleaseNote(FileType.DOC, version, product, idV);
  }

}
