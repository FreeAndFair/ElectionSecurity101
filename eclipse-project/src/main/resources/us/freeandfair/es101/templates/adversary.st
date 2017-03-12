adversary(election) ::= <<
$tally(election.tally)$
<div align="center">
<h4>$election.manipulatedVotePercentage$% of the $election.voteCount$ votes
cast in this election have been hacked.</h4>

<button type="button" class="big_button" name="Hack" 
 onclick="window.location.href='/manipulation'">Hack a Vote!</button>
>>
