package by.danceform.app.web.rest.competition;

import by.danceform.app.dto.competition.CompetitionDTO;
import by.danceform.app.security.AuthoritiesConstants;
import by.danceform.app.service.competition.CompetitionService;
import by.danceform.app.web.rest.util.HeaderUtil;
import by.danceform.app.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Competition.
 */
@RestController
@RequestMapping("/api/competitions")
@Secured(AuthoritiesConstants.ADMIN)
public class CompetitionResource {

    private final Logger log = LoggerFactory.getLogger(CompetitionResource.class);

    @Inject
    private CompetitionService competitionService;

    /**
     * POST  /competitions : Create a new competition.
     *
     * @param competitionDTO the competitionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new competitionDTO, or with status 400 (Bad Request) if the competition has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CompetitionDTO> createCompetition(@Valid @RequestBody CompetitionDTO competitionDTO)
        throws URISyntaxException {
        log.debug("REST request to save Competition : {}", competitionDTO);
        if(competitionDTO.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("competition",
                    "idexists",
                    "A new competition cannot already have an ID"))
                .body(null);
        }
        CompetitionDTO result = competitionService.save(competitionDTO);
        return ResponseEntity.created(new URI("/api/competitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("competition", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /competitions : Updates an existing competition.
     *
     * @param competitionDTO the competitionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated competitionDTO,
     * or with status 400 (Bad Request) if the competitionDTO is not valid,
     * or with status 500 (Internal Server Error) if the competitionDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CompetitionDTO> updateCompetition(@Valid @RequestBody CompetitionDTO competitionDTO)
        throws URISyntaxException {
        log.debug("REST request to update Competition : {}", competitionDTO);
        if(competitionDTO.getId() == null) {
            return createCompetition(competitionDTO);
        }
        CompetitionDTO result = competitionService.save(competitionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("competition", competitionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /competitions : get all the competitions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of competitions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CompetitionDTO>> getAllCompetitions(Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of Competitions");
        Page<CompetitionDTO> page = competitionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/competitions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /competitions/:id : get the "id" competition.
     *
     * @param id the id of the competitionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the competitionDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CompetitionDTO> getCompetition(@PathVariable Long id) {
        log.debug("REST request to get Competition : {}", id);
        CompetitionDTO competitionDTO = competitionService.findOne(id);
        return Optional.ofNullable(competitionDTO)
            .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /competitions/:id : delete the "id" competition.
     *
     * @param id the id of the competitionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        log.debug("REST request to delete Competition : {}", id);
        competitionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("competition", id.toString())).build();
    }

}
