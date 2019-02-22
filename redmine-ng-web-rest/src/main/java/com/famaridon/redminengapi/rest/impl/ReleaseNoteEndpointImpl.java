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
  public Response releaseNoteZip() {
   return releaseNoteService.generateReleaseNote(FileType.ZIP);
   /*File file = releaseNoteService.generateReleaseNote();
   return Response.ok(file,"application/msword").header("Content-Disposition", "attachment; filename=\"filename.doc\"").build();*/
  }

  @Override
  public Response releaseNotePdf() {
    return releaseNoteService.generateReleaseNote(FileType.PDF);
  }

  @Override
  public Response releaseNoteDoc() {
    return releaseNoteService.generateReleaseNote(FileType.DOC);
  }
}
