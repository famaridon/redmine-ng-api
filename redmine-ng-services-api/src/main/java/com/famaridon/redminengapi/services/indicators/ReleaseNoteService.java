package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import javax.ws.rs.core.Response;


/**
 * Release note service
 */
public interface ReleaseNoteService {

  Response generateReleaseNote(FileType type, String version, String product);

  Response getVersion();

}
