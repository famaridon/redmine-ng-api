package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import javax.ws.rs.core.SecurityContext;


/**
 * Release note service
 */
public interface ReleaseNoteService {

  File generateReleaseNote(FileType type, String apiKey, Version version, SecurityContext securityContext);

}
