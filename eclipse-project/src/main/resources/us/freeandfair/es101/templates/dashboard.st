dashboard(election) ::= <<
<div align="center">
<h2>$election.name$ &ndash; $election.date$</h2>
<h3>$election.description$</h3>

<h3><em>Candidates:</em> $election.candidates:{ candidate | &nbsp; &nbsp; &nbsp; $candidate$ }$</h3>

<p>$election.voteCount$ votes have been cast in this election. Adversaries have manipulated 
$election.voteManipulationCount$ votes and $election.receiptManipulationCount$ 
voter receipts/VVPATs.</p>

<p>$tally(election.tally)$</p>

<!-- <h3>You can be a <a href="/voting_system_choice">voter</a> or an 
     <a href="/adversary">adversary.</h3> -->

</div>
>>
