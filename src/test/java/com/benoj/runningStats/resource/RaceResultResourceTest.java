package com.benoj.runningStats.resource;

import com.benoj.runningStats.ResultsWriter;
import com.benoj.runningStats.model.RaceResult;
import com.benoj.runningStats.resources.ResultsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.Response;
import java.util.List;

import static com.benoj.runningStats.factories.RaceResultFactory.aRaceResult;
import static com.benoj.runningStats.util.createCSVAndPost;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RaceResultResourceTest {


    private static final ArgumentCaptor<List<RaceResult>> WRITTEN_RESULTS = new ArgumentCaptor<List<RaceResult>>();

    private ResultsWriter resultsWriter = mock(ResultsWriter.class);


    @Rule
    public final ResourceTestRule resources =
            ResourceTestRule.builder()
                    .addProvider(MultiPartFeature.class)
                    .addResource(new ResultsResource(resultsWriter)).build();


    @Test
    public void shouldAcceptWhenFileSent() {
        Response response = createCSVAndPost(resources.client());
        assertThat(response.getStatus(), equalTo(HttpStatus.CREATED_201));
    }

    @Test
    public void shouldParseDataAndWrite() {
        RaceResult expectedWinner = aRaceResult().create();
        RaceResult expectedRunnerUp = aRaceResult().create();

        createCSVAndPost(resources.client(), expectedWinner, expectedRunnerUp);
        verify(resultsWriter).write(WRITTEN_RESULTS.capture());
        List<RaceResult> results = WRITTEN_RESULTS.getValue();
        assertThat(results.size(), is(2));
        assertThat(results.get(0), sameBeanAs(expectedWinner));
        assertThat(results.get(1), sameBeanAs(expectedRunnerUp));
    }

}


