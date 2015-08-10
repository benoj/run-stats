package com.benoj.runningStats;

import com.benoj.runningStats.model.RaceResult;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.join;

public class util {

        public static Response createCSVAndPost(final Client client, final RaceResult... results) {
            client.register(MultiPartFeature.class);
                String csv = createResultsCSVContent(results);
                InputStream stream = new ByteArrayInputStream(csv.getBytes(Charset.forName("UTF-8")));
            FormDataMultiPart part = new FormDataMultiPart().field("file", stream, MediaType.valueOf("text/csv"));
            WebTarget target = client.target("/results");
            return target.request(MediaType.MULTIPART_FORM_DATA_TYPE).post(Entity.entity(part, part.getMediaType()));
        }

        private static String createResultsCSVContent(final RaceResult... results) {
            Object[] commaSeparatedResultsString = Arrays.stream(results).map(result ->
                    format(
                            "%s,%s,%s,%s,%s,%s,%s,%s",
                            result.getPosition(),
                            result.getBib(),
                            result.getAthlete(),
                            result.getGender(),
                            result.getCategory(),
                            result.getClub(),
                            result.getLocal(),
                            result.getFinish()
                    )
            ).toArray();
            return "Pos,Bib,Athlete,Gender,Category,Club,Locals,Finish\n"
                    + join(commaSeparatedResultsString,'\n');
        }
}
