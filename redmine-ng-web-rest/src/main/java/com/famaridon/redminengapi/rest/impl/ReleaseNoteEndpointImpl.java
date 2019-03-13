package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.ReleaseNoteEndpoint;
import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
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

  @Override
  public Response releaseNoteZip(String version, String product) {
   return releaseNoteService.generateReleaseNote(FileType.ZIP, version, product);
  }

  @Override
  public Response releaseNotePdf(String version, String product) {
    return releaseNoteService.generateReleaseNote(FileType.PDF, version, product);
  }

  @Override
  public Response releaseNoteDoc(String version, String product) {
    return releaseNoteService.generateReleaseNote(FileType.DOC, version, product);
  }

  @Override
  public Response releaseNoteVersion() {
    return releaseNoteService.getVersion();
  }
}
