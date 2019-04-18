package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.ReleaseNoteEndpoint;
import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.redmine.VersionsService;
import java.io.File;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

  private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseNoteEndpointImpl.class);

  @Override
  public Response releaseNoteZip(String apiKey, Long versionId, SecurityContext securityContext) {
    try {
      FileType type = FileType.ZIP;
      File file = releaseNoteService.generateReleaseNote(type, apiKey, versionsService.findById(apiKey,versionId),securityContext);
      return Response
          .ok(file,type.getMimeType())
          .header("Content-Disposition", "attachment; filename=\""+file.getName()+"\"")
          .build();
    } catch (IOException e) {
      LOGGER.error("version not find", e);
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Response releaseNotePdf(String apiKey, Long versionId, SecurityContext securityContext) {
    try {
      FileType type = FileType.PDF;
      File file = releaseNoteService.generateReleaseNote(type, apiKey, versionsService.findById(apiKey,versionId),securityContext);
      return Response
          .ok(file,type.getMimeType())
          .header("Content-Disposition", "attachment; filename=\""+file.getName()+"\"")
          .build();
    } catch (IOException e) {
      LOGGER.error("version not find", e);
      return Response.noContent().build();
    }
  }

  @Override
  public Response releaseNoteDoc(String apiKey, Long versionId, SecurityContext securityContext) {
    try {
      FileType type = FileType.DOC;
      File file = releaseNoteService.generateReleaseNote(type, apiKey, versionsService.findById(apiKey,versionId),securityContext);
      return Response
          .ok(file,type.getMimeType())
          .header("Content-Disposition", "attachment; filename=\""+file.getName()+"\"")
          .build();
    } catch (IOException e) {
      LOGGER.error("version not find", e);
      throw new IllegalStateException(e);
    }
  }

}
