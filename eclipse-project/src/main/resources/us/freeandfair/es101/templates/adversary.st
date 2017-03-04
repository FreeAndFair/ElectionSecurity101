adversary(election) ::= <<
<div align="center">
<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

<p>The current <b>unmanipulated results</b> of this election are:
$tally(election.my_tally.my_legitimate_tally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.my_tally.my_hacked_tally)$</p>

<h2><a href="/manipulation">Manipulate a Vote</a></h2>
</div>
>>
