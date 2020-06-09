package com.rcore.restapi.web.endpoints.routes;

import com.rcore.restapi.routes.BaseNotSecureApiRoutes;

public class FileRoutes {
    public static final String ROOT = BaseNotSecureApiRoutes.V1 + "/file";
    public static final String BY_ID = ROOT + "/{id}";
    public static final String UPLOAD = ROOT + "/upload";
}
