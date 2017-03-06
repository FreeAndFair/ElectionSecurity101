voter(election) ::= <<
<div align="center">
<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>

<p>$voting_system_choice(election.votingSystems)$</p>
</div>
>>
