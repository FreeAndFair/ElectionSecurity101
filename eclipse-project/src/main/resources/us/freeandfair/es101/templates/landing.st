landing(election) ::= <<
$header(election)$

<div align="center">
<button type="button" class="big_button" name="Vote"
 onclick="window.location.href='/voting_system_choice'">Vote!</button>
<button type="button" class="big_button" name="Hack"
 onclick="window.location.href='/adversary'">Hack!</button>
</div>

<h4>$election.voteManipulationCount$ of $election.voteCount$ votes cast in this election have been hacked.</h4>
</div>
>>
