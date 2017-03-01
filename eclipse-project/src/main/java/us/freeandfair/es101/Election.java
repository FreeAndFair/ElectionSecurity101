/**
 * Free & Fair Election Security 101 System
 * 
 * @title ElectionSecurity101
 * @created Mar 1, 2017
 * @copyright 2017 Free & Fair
 * @license BSD 3-Clause License
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @description A web-based demonstration that illustrates the relative 
 * security of various voting methods in common use in the United States.
 */
package us.freeandfair.es101;

import java.util.Vector;

/**
 * @description An election is a formal indication of choices in one or more contests.
 */
public class Election {
	Voter my_voter;
	Adversary my_adversary;
	VotingSystem my_voting_system;
	Vector<Vote> my_votes;
	Tally my_tally;
}
