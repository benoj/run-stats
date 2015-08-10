package com.benoj.runningStats.resources;

import com.benoj.runningStats.ResultsWriter;
import com.benoj.runningStats.model.RaceResult;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/")
public class ResultsResource {

    private final ResultsWriter resultsWriter;

    @Context
    Request request;

    public ResultsResource(final ResultsWriter resultsWriter) {
        this.resultsWriter = resultsWriter;
    }

    @POST
    @Path("/results")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadResults(@FormDataParam("file") InputStream stream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(RaceResult.class).withSkipFirstDataRow(true);
        MappingIterator<RaceResult> raceResults = mapper
                .reader(RaceResult.class)
                .with(schema)
                .readValues(stream);

        resultsWriter.write(raceResults.readAll());
        return Response.status(HttpStatus.CREATED_201).build();
    }
}
