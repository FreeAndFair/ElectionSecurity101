i_voted(election, voter_action) ::= <<
<div align="center">


<p>The current <b>legitimate results</b> of this election are:
$tally(election.tally.legitimateTally)$</p>
<p>The current <b>hacked results</b> of this election are:
$tally(election.tally.hackedTally)$</p>
<p>There have been $election.voteManipulationCount$ manipulated votes and
$election.receiptManipulationCount$ manipulated receipts/VVPATs.</p>
<p>Note that it takes some time for votes to be tallied, and your vote may not yet appear in these totals.</p>

<h2><a href="/">Thank You for Voting!</a></h2>
</div>
>>