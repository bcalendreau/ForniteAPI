package com.brunocalendreau.rest.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), 400);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
