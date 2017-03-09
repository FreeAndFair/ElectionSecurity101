landing(election) ::= <<
<div align="center">
<h2>$election.name$ &ndash; $election.date$</h2>
<h3>$election.description$</h3>

<button type="button" class="big_button" name="Vote"
 onclick="window.location.href='/voting_system_choice'">Vote!</button>
<button type="button" class="big_button" name="Hack"
 onclick="window.location.href='/adversary'">Hack!</button>

<p>$election.voteCount$ votes have been cast in this election. $election.voteManipulationCount$ votes and 
$election.receiptManipulationCount$ records have been hacked.</p>
</div>
>>
