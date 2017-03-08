adversary(election) ::= <<
<div align="center">
<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated voter receipts/VVPATs.</p>

<h2><a href="/manipulation">Manipulate a Vote</a></h2>
</div>
>>
