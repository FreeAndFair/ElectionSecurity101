landing(election) ::= <<
$header(election)$

<div align="center">
<button type="button" name="Vote"
 onclick="window.location.href='/voting_system_choice'">Vote!</button>
<button type="button" name="Hack"
 onclick="window.location.href='/adversary'">Hack!</button>
</div>

<h4>$election.voteCount$ votes have been cast in this election.</h4>
<h4>$election.voteManipulationCount$ votes and 
$election.receiptManipulationCount$ records have been hacked.</h4>
>>
