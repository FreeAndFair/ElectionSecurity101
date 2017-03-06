dashboard(election) ::= <<
<div align="center">
<b>$election.my_name$</b>
<i>$election.my_date$</i>

<p>In this election we have seen $election.my_total_voters$ submitting 
$election.my_total_votes$.</p>

<p>Unfortunately, an adversary has attempted to manipulate the election to ill ends
$election.manipulation_count$ times!</p>

<p> Consequently, the current <b>legitimate results</b> of this election are:
$tally(election.my_legitimate_tally)$
But the current <b>hacked results</b> of this election are:
$tally(election.my_hacked_tally)$
</div>
>>
