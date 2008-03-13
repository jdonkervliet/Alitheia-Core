/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2007-2008 by the SQO-OSS consortium members <info@sqo-oss.eu>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package eu.sqooss.impl.service.security;

import java.util.Collection;

import eu.sqooss.impl.service.security.utils.ServiceUrlManagerDatabase;
import eu.sqooss.service.db.DBService;
import eu.sqooss.service.db.ServiceUrl;
import eu.sqooss.service.logging.Logger;
import eu.sqooss.service.security.ServiceUrlManager;

public class ServiceUrlManagerImpl implements ServiceUrlManager {

    private ServiceUrlManagerDatabase dbWrapper;
    private Logger logger;
    
    public ServiceUrlManagerImpl(DBService db, Logger logger) {
        super();
        this.dbWrapper = new ServiceUrlManagerDatabase(db);
        this.logger = logger;
    }

    public ServiceUrl createServiceUrl(String url) {
        logger.info("Create service url! url: " + url);
        ServiceUrl newServiceUrl = new ServiceUrl();
        newServiceUrl.setUrl(url);
        if (dbWrapper.createServiceUrl(newServiceUrl)) {
            return newServiceUrl;
        } else {
            return null;
        }
    }

    public void deleteServiceUrl(long serviceUrlId) {
        logger.info("Delete service url! url's id: " + serviceUrlId);
        dbWrapper.deleteServiceUrl(getServiceUrl(serviceUrlId));
    }

    public ServiceUrl getServiceUrl(long serviceUrlId) {
        logger.info("Get service url! url's id: " + serviceUrlId);
        return dbWrapper.getServiceUrl(serviceUrlId);
    }

    public ServiceUrl[] getServiceUrls() {
        logger.info("Get service urls!");
        return convertServiceUrls(dbWrapper.getServiceUrls());
    }

    private static ServiceUrl[] convertServiceUrls(Collection<?> serviceUrls) {
        if (serviceUrls != null) {
            ServiceUrl[] result = new ServiceUrl[serviceUrls.size()];
            serviceUrls.toArray(result);
            return result;
        } else {
            return null;
        }
    }
    
}

//vi: ai nosi sw=4 ts=4 expandtab
