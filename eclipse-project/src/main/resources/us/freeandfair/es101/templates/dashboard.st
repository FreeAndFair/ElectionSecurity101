dashboard(election) ::= <<
<div align="center">
<b>$election.my_name$</b>
<i>$election.my_date$</i>

<p>$election.voteCount$ votes have been cast in this election.</p>

<p>Unfortunately, adversaries have manipulated $election.manipulatedVoteCount$ votes and
$election.manipulatedReceiptCount$ voter receipts/VVPATs!</p>

<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>
However, the current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$
</p>
</div>
>>
