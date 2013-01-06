package com.omdbapi;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.omdbapi.Omdb;
import com.omdbapi.OmdbMovieNotFoundException;
import com.omdbapi.SearchResult;

public class TestOmdb {

	@Before
	public void setup() {
		Logger logger = Logger.getLogger("omdb");
		logger.setLevel(Level.FINE);
	}
	@Test
	public void testForStarWars() throws OmdbMovieNotFoundException, OmdbConnectionErrorException, OmdbSyntaxErrorException {
		List<SearchResult> results = new Omdb().search("Star wars");
		assertEquals(5, results.size());
	}

	@Test(expected=OmdbMovieNotFoundException.class)
	public void testForDoesNotExist() throws OmdbMovieNotFoundException, OmdbConnectionErrorException, OmdbSyntaxErrorException {
		new Omdb().search("doesnot exist");
	}
	
	@Test
	public void testFindBladeRunnerWithYear() throws OmdbMovieNotFoundException, OmdbConnectionErrorException, OmdbSyntaxErrorException {
		List<SearchResult> search = new Omdb().year(1982).search("blade");
		assertEquals(1, search.size());
		assertEquals("Blade Runner", search.get(0).getTitle());
		assertEquals("1982", search.get(0).getYear());
	}
	
	@Test(expected=OmdbMovieNotFoundException.class)
	public void testGetWithNotExisting() throws OmdbConnectionErrorException, OmdbSyntaxErrorException, OmdbMovieNotFoundException {
		new Omdb().getById("tt");
	}
	
	@Test
	public void testGet() throws OmdbConnectionErrorException, OmdbSyntaxErrorException, OmdbMovieNotFoundException {
		RawMovie byId = new Omdb().getById("tt0083658");
		assertEquals("tt0083658", byId.imdbId);
	}
}
