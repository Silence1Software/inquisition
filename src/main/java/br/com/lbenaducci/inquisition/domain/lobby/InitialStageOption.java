package br.com.lbenaducci.inquisition.domain.lobby;

import br.com.lbenaducci.inquisition.domain.match.stage.DayStage;
import br.com.lbenaducci.inquisition.domain.match.stage.InitialStage;
import br.com.lbenaducci.inquisition.domain.match.stage.NightStage;

public enum InitialStageOption {
	DAY {
		@Override
		public DayStage getStage() {
			return new DayStage();
		}
	},
	NIGHT {
		@Override
		public NightStage getStage() {
			return new NightStage();
		}
	};

	public abstract InitialStage<?, ?> getStage();
}
