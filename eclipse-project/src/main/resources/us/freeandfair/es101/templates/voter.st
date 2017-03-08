voter(election) ::= <<
<div align="center">
<h2>$election.name$ - $election.date$</h2>

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>

<p>$voting_system_choice(election.votingSystems)$</p>
</div>
>>
