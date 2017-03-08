voter(election) ::= <<
<div align="center">
<h2>$election.name$ - $election.date$</h2>

<p>$tally(election.tally)$</p>

<p>$voting_system_choice(election.votingSystems)$</p>
</div>
>>
