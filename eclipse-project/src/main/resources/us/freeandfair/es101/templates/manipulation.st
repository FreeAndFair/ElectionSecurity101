manipulation(election, voter_action) ::= <<
<div align="center">
<p>The user chose a $voter_action.votingSystem.name$ voting system.

$if(voter_action.votingSystem.voteVisible)$
They voted for:</p>
<h3>$voter_action.vote$</h3>
$else$
You can't even see who they voted for!</p>
$fi$

$if(voter_action.votingSystem.voteManipulable)$
  $if(voter_action.votingSystem.receiptManipulable)$
    <!-- can manipulate both vote and receipt -->
    <p>You can manipulate both their vote and their $voter_action.votingSystem.receiptName$!</p>
  $else$
    <!-- can manipulate only vote -->
    <p>You can manipulate their vote, but not their $voter_action.votingSystem.receiptName$.</p>
  $fi$
$elseif(voter_action.votingSystem.receiptManipulable)$
  <!-- can manipulate only receipt -->
  <p>You can manipulate their $voter_action.votingSystem.receiptName$, but not their vote.</p>
$else$
  <!-- can't manipulate anything -->
  <p>You can't manipulate anything!</p>
$fi$
</div>
>>